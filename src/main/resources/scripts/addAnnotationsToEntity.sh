#!/bin/bash


# shellcheck disable=SC2046
echo $(pwd)


entityIdentifier="public class ShoppingItem {"
entityAnnotation="@jakarta.persistence.Entity public class ShoppingItem {"

nameIdentifier="  private String name;"
nameAnnotation="  @jakarta.persistence.Column(nullable = false, unique = true) private String name;"

amountIdentifier="  private Integer amount;"
amountAnnotation="  @jakarta.persistence.Column(nullable = false) private Integer amount;"

idIdentifier="  private Long id;"
idAnnotation="  @jakarta.persistence.Id @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO) private Long id;"

sed -i -e "s/$entityIdentifier/$entityAnnotation/g" ShoppingItem.java
sed -i -e "s/$nameIdentifier/$nameAnnotation/g" ShoppingItem.java
sed -i -e "s/$amountIdentifier/$amountAnnotation/g" ShoppingItem.java
sed -i -e "s/$idIdentifier/$idAnnotation/g" ShoppingItem.java
