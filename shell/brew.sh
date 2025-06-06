#!/bin/bash

# Mock commands so the joke runs without errors
boil() { cat; }
coffee_filter() { cat; }
add() { cat; }

# Simulate fake input
mkdir -p /tmp/fake_dev/tap
echo "💧 water" > /tmp/fake_dev/tap/water

# Actually run the brew pipeline
cat /tmp/fake_dev/tap/water | boil | coffee_filter | add coffee > /tmp/cup

# Success message and ASCII coffee cup with animated steam
if [ $? -eq 0 ]; then
  cup="
  ........
  |      |]
  \      /   
   \`----'"

  steam_frames=(
"   ( (
    ) )"
"    ) )
   ( ("
"   ( )
    ( )"
"    )(
   ()"
  )

  for i in {1..20}; do
    # Move cursor to top-left and clear screen
    printf "\033[H\033[J"
    echo "Brewed!"
    echo
    echo "${steam_frames[i % ${#steam_frames[@]}]}"
    echo "$cup"
    sleep 0.2
  done
fi

# Clean up
rm -rf /tmp/fake_dev "$CUP_FILE"
