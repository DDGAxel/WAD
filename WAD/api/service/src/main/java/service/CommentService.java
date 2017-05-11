package service;

import persistence.model.CommentModel;

public interface CommentService {

	void create(CommentModel comment, int productId);

}
