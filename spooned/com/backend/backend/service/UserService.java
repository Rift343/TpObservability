package com.backend.backend.service;
@org.springframework.stereotype.Service
public class UserService {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(UserService.class);

    @org.springframework.beans.factory.annotation.Autowired
    private com.backend.backend.repository.UsersRepository usersRepository;

    public com.backend.backend.entities.User addUser(com.backend.backend.entities.User u) {
        logger.info("method addUser-u=" + u + "");
        return usersRepository.save(u);
    }
}
