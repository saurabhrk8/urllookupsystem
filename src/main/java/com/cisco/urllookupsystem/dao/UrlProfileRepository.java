package com.cisco.urllookupsystem.dao;

import com.cisco.urllookupsystem.entity.UrlProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlProfileRepository extends JpaRepository<UrlProfile, Long> {

}
