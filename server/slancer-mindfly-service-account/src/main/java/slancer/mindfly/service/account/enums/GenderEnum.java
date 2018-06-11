package slancer.mindfly.service.account.enums;

import slancer.mindfly.core.enumhandler.IIntEnum;

/**
 * class GenderEnum
 *
 * @author xiayy860612
 * @date 2018/5/19
 */
public enum GenderEnum implements IIntEnum {
    Unknown(0),
    Male(1),
    Female(2),;

    @Override
    public int getValue() {
        return this.value;
    }

    private int value;

    GenderEnum(int value) {
        this.value = value;
    }
}
