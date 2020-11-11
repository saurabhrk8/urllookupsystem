package com.cisco.urllookupsystem.dao;

import com.cisco.urllookupsystem.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("SELECT t FROM Url t WHERE t.hostName = ?1 ")
    Url findByHostName(String hostName);

}
