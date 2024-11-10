package nl.rabobank.mithun.assessment.authentication.service;

import nl.rabobank.mithun.assessment.authentication.TestUtils;
import nl.rabobank.mithun.assessment.authentication.exception.AuthenticationException;
import nl.rabobank.mithun.assessment.authentication.model.Membership;
import nl.rabobank.mithun.assessment.authentication.model.Status;
import nl.rabobank.mithun.assessment.authentication.repository.AuthenticationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/** <p> Test class for {@link AuthenticationService }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    AuthenticationRepository repository;

    @Mock
    Membership membership;

    @Test
    void testCreateMembership() throws AuthenticationException, javax.naming.AuthenticationException {
        when(repository.save(any())).thenReturn(membership);
        authenticationService.updateMembershipData(TestUtils.getCustomerEvent("createCustomer"));
    }

    @Test
    void testUpdateMembership() throws AuthenticationException {
        when(repository.save(any())).thenReturn(membership);
        when(repository.getMemberDetailsByCustomerId(anyInt())).thenReturn(membership);
        authenticationService.updateMembershipData(TestUtils.getCustomerEvent("updateCustomer"));
    }

    @Test
    void testUpdateMembershipForInvalidCustomer(){
        when(repository.getMemberDetailsByCustomerId(anyInt())).thenReturn(null);
        assertThrows(AuthenticationException.class,()->authenticationService.updateMembershipData(TestUtils.getCustomerEvent("updateCustomer")));
    }

    @Test
    void testIsMembershipActive(){
        when(repository.getMemberDetailsByCustomerId(1)).thenReturn(TestUtils.getMembership(Status.ACTIVE));
        assertTrue(authenticationService.isMembershipActive(TestUtils.getTimelineEvent()));
    }

    @Test
    void testIsMembershipInactive(){
        when(repository.getMemberDetailsByCustomerId(1)).thenReturn(TestUtils.getMembership(Status.INACTIVE));
        assertFalse(authenticationService.isMembershipActive(TestUtils.getTimelineEvent()));
    }

    @Test
    void testIsMembershipNull(){
        when(repository.getMemberDetailsByCustomerId(1)).thenReturn(null);
        assertFalse(authenticationService.isMembershipActive(TestUtils.getTimelineEvent()));
    }

    @Test
    void testDeleteMembershipEvent() throws AuthenticationException {
        when(repository.getMemberDetailsByCustomerId(anyInt())).thenReturn(membership);
        authenticationService.updateMembershipData(TestUtils.getCustomerEvent("deleteCustomer"));
    }

    @Test
    void testDeleteMembershipForInvalidCustomer(){
        when(repository.getMemberDetailsByCustomerId(anyInt())).thenReturn(null);
        assertThrows(AuthenticationException.class,()->authenticationService.updateMembershipData(TestUtils.getCustomerEvent("deleteCustomer")));
    }

    @Test
    void testDefaultCase(){
        authenticationService.updateMembershipData(TestUtils.getCustomerEvent("default"));
    }
}
