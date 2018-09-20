package com.thoughtworks.springExercise.repository;

import com.thoughtworks.springExercise.domain.Contact;

public interface ConcatRepository {
    Contact createContactForUser(int id, Contact contact);

}
