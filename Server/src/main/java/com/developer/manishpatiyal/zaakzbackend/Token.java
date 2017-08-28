package com.developer.manishpatiyal.zaakzbackend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Gaurav on 28/8/17.
 */
@Entity
public class Token {

    @Id
    String tokenId;
    String userId;
}
