package slancer.mindfly.service.account.error;

import slancer.mindfly.common.error.ServiceErrorTypeEnum;
import slancer.mindfly.core.errors.IErrorCodeEnum;

/**
 * enum AccountErrorCodeEnum
 *
 * @author xiayy860612
 * @date 2018/5/20
 */
public enum AccountErrorCodeEnum implements IErrorCodeEnum {

    //
    UnAuth(50),
    NotSetUserEntityDuringAuth(51),

    UserNameOrPasswordFormatInvalid(101),
    UserNameAccountExisted(102),
    UserNameAccountNotExisted(103),;

    private int code;

    AccountErrorCodeEnum(int code) {
        this.code = code;
    }

    @Override
    public int getTypeCode() {
        return ServiceErrorTypeEnum.AccountService.getValue();
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
