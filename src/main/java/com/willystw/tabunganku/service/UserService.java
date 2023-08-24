package com.willystw.tabunganku.service;

import com.willystw.tabunganku.model.User;
import com.willystw.tabunganku.repository.UsersRepository;
import com.tabunganku.jooq.model.tables.records.UsersRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
  private final LongIdGenerator idGenerator;
  private final UsersRepository usersRepository;

  public UserService(LongIdGenerator idGenerator,
      UsersRepository usersRepository) {
    this.idGenerator = idGenerator;
    this.usersRepository = usersRepository;
  }

  private boolean isUserUnique(String username, String email) {
    return usersRepository.isUserAlreadyExists(username, email);
  }

  public Long insertData(String username, String email) {
    if (!isUserUnique(username, email)) {
      long id = idGenerator.nextId();
      usersRepository.save(id, username, email);

      return id;
    }
    return null;
  }

  public User getById(long id) {
    UsersRecord record = usersRepository.getById(id);

    if (record != null) {
      User user = new User();
      user.setUserName(record.getUsername());
      user.setUserId(record.getId());
      user.setEmail(record.getEmail());

      return user;
    }

    return null;
  }
}
