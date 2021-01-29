# facebook-clone
Facebook-clone application based on Facebook.

# 1. Description
The purpose of creating this project is to broaden my programming skills. RestAPI as Spring Boot application which gives information abours users, posts, comments.

# 2. Assumptions

- Display posts, posts with comments, users on specified endpoints
- Security by Spring Security and JWT
- Caching implemented by Spring Cache
- Simplification of code creation by Lombok
- Create and use MySQL Database
- Use Luquibase
- Application sends mails with activation link for new accounts

# 3. Classes and methods
Scheme: 
- Package
  - Class
    - Method 
    
- config
  - Config - enables caching
  - JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter
    - attemptAuthentication(HttpServletRequest request, HttpServletResponse response) - tryes to authenticate user
    - getToken(StringBuilder sb)  - gets token with email and password 
  - JwtAuthorizationFilter extends BasicAuthenticationFilter
    - getAuthentication(HttpServletRequest request) - verifies jwt token and returns authentication 
  - RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
    - onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) - while login is succesful it creates jwt token and returns it   
  - SecurityConfig extends WebSecurityConfigurerAdapter
    - configure(HttpSecurity http) - specifies access for endpoints and way to authenticate users
    - authenticationFilter() - specifies authentication failure and success
    - configureGlobal(AuthenticationManagerBuilder auth) - configures password encoding
- controller
  - AuthController
    - signup(@RequestBody RegisterRequest registerRequest) - starts authService.signup() method, returns http response
    - verifyAccount(@PathVariable String token) - starts authService.verifyAccount, returns https response
    - login(@RequestBody LoginRequest loginRequest)
  - CommentController
    - save(@RequestBody CommentRequest commentRequest) - starts commentService.save() method, which save given comment, returns http response
  - PostController
    - findAll(@RequestParam(required = false) Integer page) - returns list of posts with comments
    - 
