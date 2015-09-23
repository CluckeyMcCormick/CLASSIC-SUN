# SUN
Classic Project
This is a group porject for COMP 350
CIEvents, web-based application is required where users are able to create and manage eventsin 
various locations such as CSUCI main campus, Santa Barbara campus, Santa Rosa Island etc., and
invite others. Info about time and place of the event should be provided on line.
The system should alert the event creator X days before the event day in case of weather condition 
change and suggests the closest day with suitable weather. 
All event participants should be notified in case of weather forecast change via email. 
Invited users should be able to decline or accept the invitation. 
Model, View Controller pattern should be utilized in implementation.


-------------------------------------------------------------------------------------------------------------------------
Preliminary Requirement Documents
The event creator has to fill out a form, consisting of {General Location, Specific Location 
(both of which are just text), 
when is event, X days before event to check whether, what specific kind of weather is good, where the location is for 
whether checking, and small description}

The event creator provide an address for determining weather. The site will compute general area and retrieve the 
weather of that area from a weather api. The site will also see how far that location is from campus and if it’s 
too far the site will warn them and require them to place the pin elsewhere.

The event creator can invite people from the site. The site send an invite email with a link back to the website.
They can accept or deny the invitation 

All emails are stored, if the event time is changed, everybody will be notified.

If they switch date, they must set a new reminder date.
