package lookids.user.common.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lookids.user.userprofile.vo.in.CommentEventVo;
import lookids.user.userprofile.vo.in.FeedEventVo;
import lookids.user.userprofile.vo.in.ReplyEventVo;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public ConsumerFactory<String, CommentEventVo> commentConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CommentEventVo> commentEventListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CommentEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(commentConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, ReplyEventVo> replyConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ReplyEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ReplyEventVo> replyEventListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ReplyEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(replyConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FeedEventVo> feedConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-join-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FeedEventVo> feedEventListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FeedEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(feedConsumerFactory());
		return factory;
	}

}
