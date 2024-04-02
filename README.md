### Hexlet tests and linter status:
[![Actions Status](https://github.com/Barlog7/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Barlog7/java-project-78/actions)

### Java CI
[![Actions Status](https://github.com/Barlog7/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/Barlog7/java-project-78/actions)

Maintainability Badge

<a href="https://codeclimate.com/github/Barlog7/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/86942072eaef2fc2a8d3/maintainability" /></a>

Test Coverage Badge

<a href="https://codeclimate.com/github/Barlog7/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/86942072eaef2fc2a8d3/test_coverage" /></a>

Валидатор данных 

Реализуйет возможность проверять строки, числа и Map валидатором. Map может содержать элемент и схему. 

Валидатор функционирует следующим образом: сначала создается объект валидатора, затем мы создаем и настраиваем схему проверки данных. После этого мы проводим проверку данных, используя ранее созданную схему. 
для проверки строки:

var v = new Validator();
var schema = v.string();

Схема для строки содержит такой набор методов:

    required() — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения
    minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа
    contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку

Пример:

var v = new Validator();

var schema = v.string();

// Пока не вызван метод required(), null и пустая строка считаются валидным
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(""); // false
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true


Для провеки числа:

var v = new Validator();
var schema = v.number();


    required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
    positive() — добавляет ограничение на знак числа. Число должно быть положительным
    range() — добавляет допустимый диапазон, в который должно попадать значение числа включая границы

Пример:


var v = new Validator();

var schema = v.number();

schema.isValid(5); // true

// Пока не вызван метод required(), null считается валидным
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10); // true


Для проверки Map:

var v = new Validator();
var schema = v.map();


    required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
    sizeof() — добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному

Пример:

var v = new Validator();

var schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(new HashMap<>()); // true
var data = new HashMap<String, String>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

    Метод shape() используется для определения свойств объекта Map и создания схемы для валидации их значений. Каждому свойству объекта Map привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные:
  

Пример:

var v = new Validator();

var schema = v.map();

// shape позволяет описывать валидацию для значений каждого ключа объекта Map
// Создаем набор схем для проверки каждого ключа проверяемого объекта
// Для значения каждого ключа - своя схема
Map<String, BaseSchema<String>> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "firstName" и "lastName"
// Имя должно быть строкой, обязательно для заполнения
schemas.put("firstName", v.string().required());
// Фамилия обязательна для заполнения и должна содержать не менее 2 символов
schemas.put("lastName", v.string().required().minLength(2));

// Настраиваем схему `MapSchema`
// Передаем созданный набор схем в метод shape()
schema.shape(schemas);

// Проверяем объекты
Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema.isValid(human1); // true

Map<String, String> human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null);
schema.isValid(human2); // false

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B");
schema.isValid(human3); // false
