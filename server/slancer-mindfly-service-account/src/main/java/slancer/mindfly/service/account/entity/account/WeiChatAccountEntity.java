package slancer.mindfly.service.account.entity.account;

import java.time.Instant;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class WeiChatAccountEntity {
	private String userId;
	private String openId;
	private Date createTime = Date.from(Instant.now());;
}
