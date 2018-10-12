package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

}
