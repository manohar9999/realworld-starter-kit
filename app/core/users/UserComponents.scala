package core.users

import authentication.AuthenticationComponents
import com.softwaremill.macwire.wire
import commons.config.{WithControllerComponents, WithExecutionContext}
import play.api.routing.Router
import play.api.routing.sird._
import core.users.controllers.UserController
import core.users.repositories.UserRepo
import core.users.services.api.{UserCreator, UserProvider}
import core.users.services._

trait UserComponents extends AuthenticationComponents with WithControllerComponents with WithExecutionContext {
  lazy val userController: UserController = wire[UserController]
  lazy val userRepo: UserRepo = wire[UserRepo]
  lazy val userCreator: UserCreator = wire[UserCreatorImpl]
  lazy val userProvider: UserProvider = wire[UserProviderImpl]
  lazy val userRegistrationService: UserRegistrationService = wire[UserRegistrationService]
  lazy val userRegistrationValidator: UserRegistrationValidator = wire[UserRegistrationValidator]

  lazy val passwordValidator: PasswordValidator = wire[PasswordValidator]
  lazy val usernameValidator: UsernameValidator = wire[UsernameValidator]
  lazy val emailValidator: EmailValidator = wire[EmailValidator]

  val userRoutes: Router.Routes = {
    case POST(p"/users") => userController.register
  }
}