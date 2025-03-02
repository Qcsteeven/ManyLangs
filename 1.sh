for ((i = 1; i < 100; ++i)); do
  if (( i % 15 == 0 )); then
    echo -n "FizzBuzz! "
  elif (( i % 3 == 0 )); then
    echo -n "Fizz! "
  elif (( i % 5 == 0 )); then
    echo -n "Buzz! "
  else
    echo -n "$i "
  fi
done