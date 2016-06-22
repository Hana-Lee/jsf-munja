package com.ihanalee.bean;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Hana Lee
 * @since 2016-06-22 21:13
 */
@Component
@Data
@RequestScope
public class NameBean {

	private String name;
}
