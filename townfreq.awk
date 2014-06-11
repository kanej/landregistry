BEGIN {
  FS="\",\""
}

{
  freq[$12]++
}

END {
  for (town in freq)
    printf "%s\t%d\n", town, freq[town]
}
