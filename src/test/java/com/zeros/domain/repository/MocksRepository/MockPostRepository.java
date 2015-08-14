package com.zeros.domain.repository.MocksRepository;

import com.zeros.domain.entity.Post;
import com.zeros.domain.repository.PostRepository;

public class MockPostRepository extends AbstractMockCrudRepository<Post,Long> implements PostRepository {
    public MockPostRepository() {
        super(Post.class);
    }
}
