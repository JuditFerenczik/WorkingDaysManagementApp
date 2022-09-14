# WorkingDaysManagementApp
Tracking the working days of the employers

Its a management system for employers and employees to track the working days of the employees. The (kotlin) application requires registration. 
Only the employers have the right to register their employees. These two roles have different rights to do. Both can change their personal data, check the previous leaves,
enter the holidays, sick leaves (for an employee its just a request which has to be accepted by his/her employer, for an employer it is immidiately accepted). 
Every day except the national holidays and weekends are considered as workday.
The employers can list their employees (add a new one or even delete a current one), list the incoming requests from their employee and they can accept or reject it.
Both they can check their calendar to see on which day they were working or out this year, each day is color-coded by the type of the day(workday, weekend, national holday,
sick leave, holiday, other are colored by different colors in the calendar)

The data what is used by the application is stored in a sqlite database.
