package com.step.service;



import com.step.entity.User;
import com.step.form.FormException;
import com.step.form.RegisterUserForm;

import java.util.Collection;

public interface UserService {
    User findUserById(Long id);
    Collection<User> getAll();
    void deleteById(Long id);
    void save(User user);
    void register(RegisterUserForm model) throws FormException;
    User getUserEmail(String email);
    Collection<User> findUsers(String name,long id);
}
