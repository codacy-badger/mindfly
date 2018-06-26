package slancer.mindfly.service.account.controller.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeichatAccountDto {
	@ApiModelProperty(value = "微信openId")
	private String openId;
}
