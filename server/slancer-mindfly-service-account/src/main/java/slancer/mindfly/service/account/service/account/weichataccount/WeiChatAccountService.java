package slancer.mindfly.service.account.service.account.weichataccount;

import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.entity.account.WeiChatAccountEntity;
import slancer.mindfly.service.account.service.account.IAccountService;

public class WeiChatAccountService implements IAccountService<WeiChatAccountEntity> {

	@Override
	public UserEntity reg(WeiChatAccountEntity info, UserEntity userInfo) {
		return null;
	}

	@Override
	public UserEntity login(WeiChatAccountEntity info) {
		return null;
	}

	@Override
	public void bind(WeiChatAccountEntity info, String userId) {

	}
}
