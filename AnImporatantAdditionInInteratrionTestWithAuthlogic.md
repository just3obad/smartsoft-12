After searching alot in testing in Integration mode with "authlogic "
I found these important result because
you need to login in the setup method meaning :

setup do    @admin1=Admin.create(email:"admin1@gmail.com",password:"123456",password\_confirmation:"123456")
> post '/admin/login', :admin\_session => {:email => 'admin1@gmail.com', :password => '123456'}
> end

then here com your tests

please note that this test is for the integration part only otherwise
kiro wiki serve others modes


######this Wiki is important these days because after that we authenticate all our pages our test will fail again############