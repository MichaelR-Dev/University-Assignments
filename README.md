# Backend-Portfolio

### Contents
1. [DriverPass Business/System Requirements](README.md#driverpass-business-system-requirements)
2. [Artemis Financial Vulnerabilities Assessment](README.md#artemis-financial-vulnerability-assessment)
2. [Grocery Tracking App](README.md#grocery-tracking-app)
3. [Gaming Room Software Design](README.md#software-design-gaming-room)
4. [Software Test Automation & QA](README.md#software-test-automation--qa)
5. [Python CRUD Module](README.md#pythong-crud-module)

### DriverPass Business System Requirements 

- Briefly summarize the DriverPass project. Who was the client? What type of system did they want you to design?

```
The client is DriverPass, a driving instruction and examination company looking to create a multi-platform application 
for students to book road tests and complete assignments/exams according to DMV regulations per locality.  The system 
will include an administrative panel for assessing users data etc, a booking interface for road testing and driving 
instructor assignment. The application will include package plans that can be created/modified/deleted by admins. 
These packages are price plans for students to utilize the application.  
```

- What did you do particularly well?

```
The curation and translation of client interviews to UML diagrams, and then to technical jargon filtered presentations 
was a strong point in my ability to accomodate to a client.
```

- If you could choose one part of your work on these documents to revise, what would you pick? How would you improve it?

```
I'd definitely revise the Use Case UML diagram, it is definitely visually too complex, and could be very simplified 
and restructured for efficiency viewing. I'd probably space out the obvious bunched systems of dependents and connect 
my actors in a way less intersecting.
```

- How did you interpret the user’s needs and implement them into your system design? Why is it so important to consider the user’s needs when designing?

```
Being able to properly filter through a user's needs and understand the functional/non-functional requirements allowed 
me a pipeline to implementation and design. It's paramount to consider the user's needs when designing as their vision 
is ultimately the end goal, and the more we can take the user's needs and implement them to the system, the more accurate 
the application will ultimately be developed. User's needs generally directly translate in some way to a developable 
system, being able to translate this is important.
```

- How do you approach designing software? What techniques or strategies would you use in the future to analyze and design a system?

```
When designing software, I approach it from a very technical standpoint, ensuring the bare bones and framework are available 
before proceeding to design. This means when having client interviews etc, we can take user's needs and metaphorically 
strip the meat from it, leaving only the skeletal necessities from which we can build off of. In the future, utilizing 
this technique will ensure that having focused on functional requirements first, we have the groundwork for reaching 
towards min/maxing our development time, vs how long it will take to reach a suitable stable deployment.
```

### Artemis Financial Vulnerability Assessment 

- Briefly summarize your client, Artemis Financial, and their software requirements. Who was the client? What issue did they want you to address?

```
Artemis Financial is a financial institution looking to bolster their security both in code and in business operation.
```

- What did you do very well when you found your client’s software security vulnerabilities? Why is it important to code securely? What value does software security add to a company’s overall wellbeing?

```
The client was failing to meet regulatory guidelines, utilize secure application endpoints, https, and data encryption. 
Secure coding is paramount to avoiding data breaches and mishandled data lawsuits. Malicious actors are always a looming 
threat and ensuring secure coding will provide bonuses to the company's overall wellbeing by ensuring security for bother 
the company and its clients.
```

- What part of the vulnerability assessment was challenging or helpful to you?

```
During the vulnerability assessment, I found it most challenging to handle version history. Having so many different 
vulnerabilities be from different dependency versions and be mitigated or exacerbated based on which tech stack was 
used on the backend made it a complex challenge.
```

- How did you increase layers of security? In the future, what would you use to assess vulnerabilities and decide which mitigation techniques to use?

```
In order to increase layers of security, SHA-512 and SSL/TLS was implemented to ensure multiple points of vulnerability 
were covered. For future reference, ensuring an updated maven-dependency-check and staying up to date with industry 
standards, vulnerability patches, and zero day vulnerabilities will determine which methods of mitigation are used.
```

- How did you make certain the code and software application were functional and secure? After refactoring the code, how did you check to see whether you introduced new vulnerabilities?

```
Ensuring the application ran without error, proper testing and debugging, and generating new dependency check reports, 
iterating this work flow over each refactorization ensured I was aware of any new vulnerabilities due to my coding.
```

- What resources, tools, or coding practices did you use that might be helpful in future assignments or tasks?

```
A list of the resources, tools, coding practices used included:

- https://nvd.nist.gov (National Vulnerability Database)
- https://jeremylong.github.io/DependencyCheck/dependency-check-maven/index.html (Maven Dependency Check Plugin)
- https://docs.oracle.com/javase/9/docs/specs/security/standard-names.html#cipher-algorithm-names (Oracle Recommended Cipher Algorithms)
- https://docs.oracle.com/javase/6/docs/technotes/tools/windows/keytool.html (Oracle Keytool Key and Certificate Management Tool)
- https://learn.snhu.edu/content/enforced/1237130-CS-305-J7785-OL-TRAD-UG.23EW3/course_documents/CS%20305%20Vulnerability%20Assessment%20Process%20Flow%20Diagram.pdf?_&d2lSessionVal=vkXVymSRbe6YFxBJ36UuSrjwm&ou=1237130 (Vulnerability Assessment Process Flow)

```

- Employers sometimes ask for examples of work that you have successfully completed to show your skills, knowledge, and experience. What might you show future employers from this assignment?

```
I believe the strongest attributes of this assignment to show of my skills/knowledge/experience would be the 
necessity of scowering the NVD for vulnerability assessment, and utilizing proper encryption implementation. 
These can be overlooked by beginner back-end developers and I believe is considered to be foundational knowledge.
```

### Grocery Tracking App

- Summarize the project and what problem it was solving.

```
This project utilizes Python embedding along with C++ to create a windows executable for parsing grocery lists with item quantity.
```

- What did you do particularly well?

```
I was most proud of, and believe I did exceptionally well with maintaining clear and concise code in both languages.  
With proper documentation, and standardizing method summaries, I believe I achieved a pretty seamless integration of C++/Python.
```

- Where could you enhance your code? How would these improvements make your code more efficient, secure, and so on?

```
Utilizing proper parsing for CSV or other serialized formats would be a huge improvement to this project.  
As is, it utilizes a line-by-line basis for the grocery list with pretty strict schema.
```

- Which pieces of the code did you find most challenging to write, and how did you overcome this? What tools or resources are you adding to your support network?

```
Getting familiar with the Python Embedding API would be a huge plus to this project.  
My biggest challenge was dealing with the Python Embedded methods beyond the basic initialize. 
Dealing with different data return types, etc. proved to be a big challenge for me to get familiar with their documentation.
```

- What skills from this project will be particularly transferable to other projects or course work?

```
Modularizing the Read/Write process for files, in both languages will be absolutely paramount for implementing moving forward. 
I really enjoyed working with read/write and iostream in module form, allowing for fast work flow in the future.
```

- How did you make this program maintainable, readable, and adaptable?

```
Ensuring I provided brief explanations to each function/method, as well as parameter types/explanations in the descriptions
above them was a huge impact on readability throughout this process.  Again, the modularity with the read/write and iostream 
made this program very adaptable and easy to minimize written code with maximum functionality.
```

### Software Design Gaming Room

- Briefly summarize The Gaming Room client and their software requirements. Who was the client? What type of software did they want you to design?
```
The Gaming Room was a client seeking software/hardware architecture design consultation. 
The client was seeking to utilize the best software/hardware architecture to support scalability and performance with a 
multi-platform (Mobile/Desktop/Web) game. 
```
- What did you do particularly well in developing this documentation?
```
Using the same method I used when highlighting the importance of a CDN/Caching game flow and how it becomes effective for 
multi-platforms with varying device limitations while also maximizing savings in laymen terms, I believe is something done 
particularly well to sell the client on recommendations.
```
- What about the process of working through a design document did you find helpful when developing the code?
```
Having a design document is a great way to ensure that during development, there is no straying in stack usage.  With the 
headspace of what other frameworks and services/architecture would be used, it's easier to be confident when developing 
the code with certain patterns.
```
- If you could choose one part of your work on these documents to revise, what would you pick? How would you improve it?
```
Revising the OS comparisons would be my first focus. Though the OS are differentiated and analyzed, I feel as if I could have 
simplified it further with bullet points and the facts for brief understanding of comparison.
```
- How did you interpret the user’s needs and implement them into your software design? Why is it so important to consider the user’s needs when designing?
```
Considering the user's needs when designing is important, because just as much as the client has constraints, the end-users 
have constraints themselves. Whether it be how the design is understood in runtime, or the overall user experience being 
fluid and well adopted on any platform, it's important to consider these when deciding on the best universally accepted 
design for all use cases. The design document itself must be suitable for developers as well to build off of, and 
this is something to also be considered which makes it a juggle to have the design document understood and curated to 
each of these 3 figures.
```
- How did you approach designing software? What techniques or strategies would you use in the future to analyze and design a similar software application?
```
In approaching the software design, it's important to build from the "ground" up. Though in the case of software design, 
these are usually hard to visualize without a picture of the end-product. If we consider the end product, and 
sort of 'reverse engineer' it in our head at a high level, it really helps to consider what type of end users we'll be 
dealing with, which is very important in moving forward with the process of "shaping" the perfect UX into the constraints 
of the client themselves. Having this high level understanding of the product concept, is the best obstacle to strategize 
and analyze around, slowly taking and giving according to the design constraints. 
```

### Software Test Automation & QA

- How can I ensure that my code, program, or software is functional and secure?
```
In order to ensure that my code/program/software is functional and secure, 
utilizing automation and unit tests can cover most avenues for Quality Assurance. 
Testing for appropriate functionality and security through iterative and modular 
automated test units is the best way to ensure covering all bases for Software criteria.
```

- How do I interpret user needs and incorporate them into a program?
```
Interpretting user needs and incorporating them into a program is both a matter of 
non-technical/technical jargon and the ability to discern requirements from unorganized context. 
One good example of this is if the user specifies they want users to not have access to an 
admin panel if they don't have the admin flag, in this scenario, we'd consider all routes 
to the admin panel and any admin functionality and ensure that a user without the flag 
can access none of these routes, this is an example of translating a client requirement 
and exhausting all scenarios involving the requirement.
```

- How do I approach designing software?
```
My approach to designing software involves a thorough pipeline with security and automation 
integrated each step of the way. Utilizing proper CI/CD and integrated Security/Testing, 
we can get a better high-level view of the Software and utilize the concepts even when designing. 
With consideration to Client Requirements first, and translating these to an airtight software design.
```

### Python CRUD Module

- How do you write programs that are maintainable, readable, and adaptable? Especially consider your work on the CRUD Python module from Project One, which you used to connect the dashboard widgets to the database in Project Two. What were the advantages of working in this way? How else could you use this CRUD Python module in the future?
```
Every Project is different, but I've found there's a few ground rules to ensure maintainable/readable/adaptable programs. Having worked on Project One in developing the Python CRUD Module, I found quite a few bad practice errors I had to omit and spaghetti code to rewrite when continuing in Project Two. Having worked on the connection of the dashboard widgets and introducing new modules throughout the course, I found the best way to produce efficient code is to plan ahead and be thoughtful before speedy. Meeting deadlines is important, but when you speed through coding and get "everything in your head out on paper", you end up producing some god awful code and although I'm getting better it was definitely evident in Project One. I won't say that my Project Two code is up to par yet, but it's a far cry from what I had previously. Ensuring you use things like pseudocode comments, proper documentation, UML diagrams and what not, whatever it is to help you to visualize your code and standardize it across modules, that will be your solution to maintainable, readable, and adaptable programs.
```

- How do you approach a problem as a computer scientist? Consider how you approached the database or dashboard requirements that Grazioso Salvare requested. How did your approach to this project differ from previous assignments in other courses? What techniques or strategies would you use in the future to create databases to meet other client requests?
```
In previous client requirement approaches (Specifically in my higher learning), I was majorly focused on producing back-end implementations. This seems to be one of the first times I developed for a more full-stack application. It was easy to ignore most of the client requirements previously as I was more focused on just producing code and not so much the full product, but this project has definitely revealed a more grand picture. I approached this new learning curve with caution and made sure I used my research/resources to the full potential before running head first into unpreparedness. In previous assignments I was quick to start programming, I had a fluid/abstract idea in my head of what I wanted to program, but this time around I was sure to be careful and actually take time to approach the client requirements for the most insightful angles which reflected well into the application. The most important aspect I think I'll take from this into similar future work is, ensure the roadmap starts with reaching the most sustainable bare minimum of the client requirements first, and then expand on it with extreme caution.
```

- What do computer scientists do, and why does it matter? How would your work on this type of project help a company, like Grazioso Salvare, to do their work better?
```
Computer Scientists have fundamental knowledge regarding the tools/methods used for solving client problems. It's in the name, computer 'scientist', where research, optimization, and solutions are born through the hard work and determination of this role. When a client like Grazioso Salvare approaches a computer scientist for a project like this, the computer scientist is expected to know the best possible solution and how to get there. They have an expertise in not just solving the problem, but ensuring it's the best possible solution. This does two things for the client, an ethical and well-guided Computer Scientist to the best of their knowledge will never recommend the client a solution that does more harm than good which is always better for companies. Secondly the Computer Scientist will familiarize itself with the company and cater to its workflow and mission, Computer Scientists don't only study the technology, but also specialize in translating the right technologies for the company to do its best to fit its productivity requirements.
```