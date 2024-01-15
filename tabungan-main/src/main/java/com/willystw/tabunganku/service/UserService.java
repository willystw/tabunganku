package com.willystw.tabunganku.service;

import com.willystw.tabunganku.model.User;
import com.willystw.tabunganku.repository.UsersRepository;
import com.tabunganku.jooq.model.tables.records.UsersRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService  {
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

  public UUID insertData(UUID id, String username, String email) {
    if (!isUserUnique(username, email)) {
      usersRepository.save(id, username, email);
      return id;
    }
    return null;
  }

  public User getById(UUID id) {
    UsersRecord record = usersRepository.getById(id);

    if (record != null) {
      User user = new User();
      user.setUsername(record.getUsername());
      user.setUserId(record.getUserId());
      user.setEmail(record.getEmail());
      user.setActive(record.getIsactive());
      user.setFirstName(record.getFirstName());
      user.setLastName(record.getLastName());

      return user;
    }

    return null;
  }
}
