class AutocompleteController < ApplicationController
  #by stating this line the controller is now  seeing the plugin of auto complete
  auto_complete_for :autocomplete, :query
end
