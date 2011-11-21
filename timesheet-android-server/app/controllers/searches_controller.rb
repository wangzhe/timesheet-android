class SearchesController < ApplicationController

  VIEW_DIR = Pathname.new(File.dirname(__FILE__) + "/../views")
  
  def show
    @site = params[:site]
    data_file = json_for "time_sheet_validation"

    render :json => File.read(data_file), :content_type => 'application/vnd.rea.localities+json'
  end

  private

  def json_for fragment
    VIEW_DIR + "searches/#{fragment}.json"
  end
  
end
