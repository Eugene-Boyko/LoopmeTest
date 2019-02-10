package com.loopme.dao;

import com.loopme.dbmodel.Application;
import com.loopme.dbmodel.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.loopme.dbmodel", "com.loopme.dao.impl", "com.loopme.mapper"})
@EntityScan(basePackageClasses = {Application.class, User.class})
class TestConfig {
}
