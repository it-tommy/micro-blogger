package org.sss.blogger.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.sss.blogger.entity.Blog;
import org.sss.blogger.entity.Item;

// Integer is the type of primary key
public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	List<Item> findByBlog(Blog blog, Pageable pageable);

}
