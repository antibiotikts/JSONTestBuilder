in console enter this: gradlew clean test3

"action": "set browser",
"browser": "firefox"

"action": "set browser size",
"browser size": "1920x1080"


action: "open"
url: "Enter your url"  open your url

"action": "close"  close browser

"action": "type" set value in laine and press enter
"selector_type": "enter your selector type" ["xpath", "element id", "element name", "css selector", "class name" ]
"selector": "Enter your selector"
"value": "Enter value"

"action": "assert" Takes the text from the element and compares it with "value"
"selector_type": "enter your selector type" ["xpath", "element id", "element name", "css selector", "class name" ]
"selector": "Enter your selector"
"value": "Enter value"

"action": "scroll to"
"selector_type": "enter your selector type" ["xpath", "element id", "element name", "css selector", "class name" ]
"selector": "Enter your selector"

"action": "should be",
"selector_type": "enter your selector type" ["xpath", "element id", "element name", "css selector", "class name" ]
"selector": "Enter your selector"
"condition":  "Enter condition" ["visible", "hidden", "exist", "empty", "selected", {"text" has "value"}, {"text" has "exactText"}]
