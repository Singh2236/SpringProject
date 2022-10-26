# Implementation of Sorting, Pagination using Spring Data JPA

# Sorting with Spring JPA

## Intro

1. Spring Data JPA supports sorting to Query results with easier configurations.
2. Spring Data JPA provides default implementations of sorting with the help of ``PagingAndSortingRepository``
   interface.
3. There are two ways to achieve sorting in the Spring Data JPA:
    1. Static Sorting
    2. Dynamic Sorting

## Static Sorting

1. Static sorting refers to the mechanism where the retrieved data is always sorted by specified columns and directions.
   The column and sort directions are defined at the development time and can not be changed at the runtime.
2. Below is an example of static sorting.

````java
List<Person> FindByOrderByNameDes();
````

## Dynamic Sorting

1. By using dynamic sorting, you can choose the sorting column and direction at runtime to sort the query results.
2. Dynamic Sorting provides more flexibility in choosing sort columns and directions with the help of ``Sort`` parameter
   to your query method. The sort class is just a specification that provides sorting options for database queries.
   Below is an example.

````java
Sort sort=Sort.by("Name").decending().and(Sort.by("age"));
```` 

# Pagination

## Introduction

1. Spring Data JPA supports pagination which helps easy to manage & display large amount of data in various pages inside
   the web application.
2. Just like the special Sort parameter, we have for the dynamic sorting, Spring Data JPA support another special
   parameter called ``Pageable`` for paginating the query results.
3. We can combine both pagination and dynamic sorting with the help of Pageable.

## Dynamic Sorting

Whenever we want to apply results, all we need to do is just add Pageable parameters to the query method definition and
set the return by Page<T> like below.
````Java
Pageable pageable = PageRequest.of(0,5, Sort.By("name").descending());
Page<Person> findByName(String name, Pagebale pageable);
````

