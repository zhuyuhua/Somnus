Account Transfer story

Meta:  
@author zhuyuhua  
@theme  account  
				 
Narrative: Transferring monkey between accounts
Scenario: Transfer monkey from settle account to saving account
Given my settle account has 1000,saving account is 2000
When I transfer 500 from settle account to saving account
Then my settle account should have 500
Then my saving account should have 2500
