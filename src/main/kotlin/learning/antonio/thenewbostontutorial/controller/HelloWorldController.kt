/*
File: HelloWorldController
Author: Antonio Masotti <masotti@bikeleasing.de>
Date: 29.04.23
*/

package learning.antonio.thenewbostontutorial.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {
        @GetMapping("/world")
        fun helloWorld() = "<h1>Hello World from SpringKotlin!</h1>"
}