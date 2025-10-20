# Assignment 3 – AI Prompts and Responses

As part of this assignment, I used AI to clarify certain design questions and to get small examples of Java syntax. Below are a few of the prompts I asked and short excerpts or summaries of the responses. 


### Prompt 1  
*"How can I split a large Java program into multiple classes for better structure?"*  

**Response (excerpt):**  
OpenAI suggested creating one class for each major responsibility in the ETL pipeline, such as a `Product` class for the data model, a `CSVExtractor` for reading input, a `Transformer` for applying rules, and a `CSVLoader` for output. It explained that this follows the principle of separation of concerns.

---

### Prompt 2  
*"What’s a simple way to throw a custom exception if an input file is missing?"*  

**Response (summary):**  
The AI gave me a short example of defining a custom exception by extending `Exception`. It also showed how to use `throw new MissingInputException("message")` when a file is not found. I used this as a reference to create my own `MissingInputException` class.

---

### Prompt 3  
*"Can you show an example of how to round decimal numbers to two places in Java?"*  

**Response (excerpt):**  
The AI provided code using `BigDecimal.setScale(2, RoundingMode.HALF_UP)` and explained why this was better than using `Math.round` for monetary values. I adapted this approach inside my transformation logic.

---

### Prompt 4  
*"How can I add Javadoc comments to my classes and methods?"*  

**Response (summary):**  
The AI explained the structure of Javadoc comments and gave a template for including `@param`, `@return`, and `@throws`. I then wrote my own Javadoc for each class and method, customizing them to match the details of my program.

---

## Reflection on AI Usage
Overall, the AI was helpful for answering small, specific questions about Java syntax and object-oriented design. I used it as a tool to confirm my approach, learn better practices (like using `BigDecimal`), and improve documentation. 