package com.gvolpe.http4s.auth.demo.endpoint

import com.gvolpe.http4s.auth.repository.TokenRepository
import com.gvolpe.http4s.auth.service.Secured
import org.http4s._
import org.http4s.dsl._

object DemoHttpEndpoint {

  def service()(implicit tokenRepository: TokenRepository) = HttpService {
    case GET -> Root / "public" =>
      Ok("Public resource")
    case req @ GET -> Root / "protected" => Secured(req) {
      Ok("Protected resource")
    }
  }

}
