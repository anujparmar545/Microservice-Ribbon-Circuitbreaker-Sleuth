package com.anuj.microservice1;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

@Entity
public class Student implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
		@Id
		private String id;
	    private String name;
	    private long followers;

	    public Student() {
	    }

	    public Student(String id,String name, long followers) {
	        this.id=id;
	    	this.name = name;
	        this.followers = followers;
	    }

	    
	    
	    public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getFollowers() {
			return followers;
		}

		public void setFollowers(long followers) {
			this.followers = followers;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
	    public String toString() {
	        return String.format("User{id=%s, name='%s', followers=%d}", id, name, followers);
	    }
    
    



}
