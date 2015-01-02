package com.impetus.client.twitter;

import java.util.List;

import javax.persistence.*;

import com.impetus.client.twitter.dao.TwitterServiceHbase;
import com.impetus.client.twitter.entities.UserHBase;

public class MyTest {

	public static void main(String[] args) throws Exception {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("twibaseTest");
		EntityManager em=emf.createEntityManager();
		
		//prepareData();
		
		Query q = em.createQuery("select u from UserHBase u ");
		List<UserHBase> users = q.getResultList();
        for(UserHBase u:users){
        	System.out.println(u.getPreference().getPrivacyLevel());
        }
		
		em.close();
		emf.close();
	}

	private static void prepareData() throws Exception {
		String userId1 = "0001";
		String userId2 = "0002";

		System.out.println("Prepare tables ...");
		TwitterServiceHbase twitter = new TwitterServiceHbase("twibaseTest");
		twitter.createEntityManager();
		System.out.println("Prepare tables done.");

		System.	out.println("Prepare data ...");
		TwitterHBaseTest test = new TwitterHBaseTest();
		test.setUpInternal("twibaseTest");
		test.addUsers();
		test.addAllUserInfo();
		test.addTweets();
		test.addExternalLinks();
		System.out.println("Prepare data done.");
	}

}
