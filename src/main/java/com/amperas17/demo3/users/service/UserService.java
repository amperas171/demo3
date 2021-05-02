package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.UserEntity;

import java.util.List;

public interface UserService {

    /**
     * Создает нового клиента
     * @param userEntity - клиент для создания
     */
    void create(UserEntity userEntity);

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<UserEntity> readAll();

    /**
     * Возвращает клиента по его ID
     * @param id - ID клиента
     * @return - объект клиента с заданным ID
     */
    UserEntity read(int id);

    /**
     * Обновляет клиента с заданным ID,
     * в соответствии с переданным клиентом
     * @param userEntity - клиент в соответсвии с которым нужно обновить данные
     * @param id - id клиента которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    UserEntity update(UserEntity userEntity, int id);

    /**
     * Удаляет клиента с заданным ID
     * @param id - id клиента, которого нужно удалить
     */
    boolean delete(int id);
}
