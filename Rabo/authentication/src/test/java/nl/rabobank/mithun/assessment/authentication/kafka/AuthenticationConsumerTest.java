package nl.rabobank.mithun.assessment.authentication.kafka;

import nl.rabobank.mithun.assessment.authentication.TestUtils;
import nl.rabobank.mithun.assessment.authentication.model.Membership;
import nl.rabobank.mithun.assessment.authentication.model.TimelineEvent;
import nl.rabobank.mithun.assessment.authentication.repository.AuthenticationRepository;
import nl.rabobank.mithun.assessment.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.AuthenticationException;


/** <p> Test class for {@link AuthenticationConsumer }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationConsumerTest {

    @InjectMocks
    AuthenticationConsumer authenticationConsumer;

    @Mock
    AuthenticationRepository repository;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    AuthenticationProducer authenticationProducer;

    @Mock
    Membership membership;

    @Test
    public void testCustomerEvents() throws AuthenticationException {
        authenticationConsumer.listenCustomerEvents(TestUtils.getStringFromObject
                (TestUtils.getCustomerEvent("updateCustomer")));
    }

    @Test
    public void testTimelineEventsWithMemberInActive() throws AuthenticationException {
        TimelineEvent timelineEvent = TestUtils.getTimelineEvent();
        authenticationConsumer.listenTimelineEvents(TestUtils.getStringFromObject
                (timelineEvent));
    }

    @Test
    public void testTimelineEventsWithMemberActive() throws AuthenticationException {
        authenticationConsumer.listenTimelineEvents(TestUtils.getStringFromObject(TestUtils.getTimelineEvent()));
    }

    @Test
    void testDLT(){
        authenticationConsumer.listenToDeadLetterQueue();
    }
}
