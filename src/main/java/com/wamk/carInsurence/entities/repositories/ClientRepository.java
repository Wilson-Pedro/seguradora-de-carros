package com.wamk.carInsurence.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wamk.carInsurence.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
}
