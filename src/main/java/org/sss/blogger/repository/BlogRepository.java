package org.sss.blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sss.blogger.entity.Blog;
import org.sss.blogger.entity.User;

// Integer is the type of primary key
public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	List<Blog> findByUser(User user);

}
