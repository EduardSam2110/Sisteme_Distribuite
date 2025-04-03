package com.sd.laborator.services

import com.sd.laborator.interfaces.PublicIPInterace
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import java.net.URL
import java.util.*
import java.util.stream.Stream
import javax.servlet.http.HttpServletRequest

class PublicIPService : PublicIPInterace{
    override fun getUserPublicIP() : String {

        val rawResponse = URL("https://api64.ipify.org/?format=json").readText()
        val responseRootObject = JSONObject(rawResponse)
        val ipAddress = responseRootObject.getString("ip")

        return ipAddress
    }
}

//
//@Component
//@RequestScope
//class PublicIPService @Autowired constructor(private val request: HttpServletRequest)  : PublicIPInterace  {
//    /**
//     * Resolves client IP address when application is behind a NGINX or other reverse proxy server
//     */
//     override fun getUserPublicIP(): String {
//        val xRealIp = request.getHeader("X-Real-IP") // used by Nginx
//        val xForwardedFor = request.getHeader("X-Forwarded-For") // used by the majority of load balancers
//        val remoteAddr = request.remoteAddr // otherwise uses the remote IP address obtained by our Servlet container
//
//        // returns the first non-null
//        return Stream.of(xRealIp, xForwardedFor, remoteAddr)
//            .filter { obj: String? -> Objects.nonNull(obj) }
//        .findFirst().orElse("NaN")
//    }
//}