package com.bits.bitsvote.repository;

import com.bits.bitsvote.entity.Elections;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Elections, Long> {

	List<Elections> findAll();
	List<Elections> findByStatus(String status);
}
