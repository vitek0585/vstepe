package com.step.service;

import com.step.entity.User;
import com.step.form.FormException;
import com.step.form.RegisterUserForm;
import com.step.form.RegisterUserFormValidation;
import com.step.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _repository;

    @Override
    public User findUserById(Long id) {
        return _repository.findOne(id);
    }

    @Override
    public Collection<User> getAll() {
        return _repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        _repository.delete(id);
    }

    @Override
    public void save(User user) {
        _repository.save(user);
    }

    @Override
    public void register(RegisterUserForm model) throws FormException {
        RegisterUserFormValidation validator = new RegisterUserFormValidation();
        MapBindingResult err = new MapBindingResult(new HashMap<String, String>(), RegisterUserForm.class.getName());
        validator.validate(model, err);
        List<ObjectError> errList = err.getAllErrors();

        if (!errList.isEmpty()) {
            throw new FormException(errList);
        } else {
            User user = new User(model.getEmail(), model.getPassword());
            _repository.save(user);
        }
    }

    @Override
    public User getUserEmail(String email) {
          return _repository.findByEmail(email);
    }

    @Override
    public Collection<User> findUsers(String name,long id) {
        return _repository.findByFilter(name,id);
    }
}
