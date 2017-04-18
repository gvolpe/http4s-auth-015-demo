package com.gvolpe.http4s.auth.demo

import com.gvolpe.http4s.auth.demo.endpoint.DemoHttpEndpoint
import com.gvolpe.http4s.auth.model.{HttpToken, HttpUser}
import com.gvolpe.http4s.auth.repository.TokenRepository
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.{Server, ServerApp}

import scalaz.concurrent.Task
import scalaz.{\/, \/-}

object Demo extends ServerApp {

  implicit val tokenRepository = new TokenRepository {
    val fakeUser = HttpUser("gvolpe", HttpUser.createToken("gvolpe"))
    println(fakeUser.httpToken) // Just to see the generated token to use in the headers
    override def find(token: HttpToken): Task[Option[HttpUser]] = Task.now(Some(fakeUser))
    override def remove(user: HttpUser): Task[\/[Throwable, Unit]] = Task.now(\/-())
    override def save(user: HttpUser): Task[\/[Throwable, Unit]] = Task.now(\/-())
  }

  override def server(args: List[String]): Task[Server] =
    BlazeBuilder
      .bindHttp(8080, "localhost")
      //.mountService(AuthHttpEndpoint.service) IT WON'T WORK BECAUSE IT'S BASED ON Http4s v0.14.6!!!
      .mountService(DemoHttpEndpoint.service)
      .start

}
