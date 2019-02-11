package com.loopme.servicebean;

import com.loopme.domainrepo.IApplicationDomainRepo;
import com.loopme.domainrepo.IUserDomainRepo;
import com.loopme.service.IAdminService;
import com.loopme.service.IOperatorService;
import com.loopme.service.IPublisherService;
import com.loopme.service.impl.AdminServiceImpl;
import com.loopme.service.impl.OperatorServiceImpl;
import com.loopme.service.impl.PublisherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IAdminService adminService(IUserDomainRepo userDomainRepo) {
        return new AdminServiceImpl(userDomainRepo);
    }

    @Bean
    public IOperatorService operatorService(IUserDomainRepo userDomainRepo, IApplicationDomainRepo applicationDomainRepo) {
        return new OperatorServiceImpl(userDomainRepo, applicationDomainRepo);
    }

    @Bean
    public IPublisherService publisherService(IApplicationDomainRepo applicationDomainRepo) {
        return new PublisherServiceImpl(applicationDomainRepo);
    }
}
