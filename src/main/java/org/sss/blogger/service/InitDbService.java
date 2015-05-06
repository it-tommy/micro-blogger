package org.sss.blogger.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.sss.blogger.entity.Blog;
import org.sss.blogger.entity.Item;
import org.sss.blogger.entity.Role;
import org.sss.blogger.entity.User;
import org.sss.blogger.repository.BlogRepository;
import org.sss.blogger.repository.ItemRepository;
import org.sss.blogger.repository.RoleRepository;
import org.sss.blogger.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blog = new Blog();
		blog.setName("Apache Tomcat");
		blog.setUrl("http://www.apachenews.org/archives/rss_apache_tomcat.xml");
		blog.setUser(userAdmin);
		blogRepository.save(blog);
		
		Item item1 = new Item();
		item1.setBlog(blog);
		item1.setTitle("First Title");
		item1.setLink("http://www.rsssearchhub.com/feeds/tomcat-feeds/");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blog);
		item2.setTitle("Second Title");
		item2.setLink("http://www.rsssearchhub.com/feeds/tomcat-feeds/");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
	}
	
	
	
	
	
	
	

}
