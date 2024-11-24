package kafka.example.authentication.kafka;

import kafka.example.authentication.TestUtils;
import kafka.example.authentication.model.Membership;
import kafka.example.authentication.model.TimelineEvent;
import kafka.example.authentication.repository.AuthenticationRepository;
import kafka.example.authentication.service.AuthenticationService;
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
