class MovieListResponse {
  var page;
  var totalResults;
  var totalPages;
  List<dynamic> results;

  MovieListResponse(
      {this.page, this.totalResults, this.totalPages, this.results});

  MovieListResponse.fromJson(Map<String, dynamic> json) {
    page = json['page'];
    totalResults = json['total_results'];
    totalPages = json['total_pages'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['page'] = this.page;
    data['total_results'] = this.totalResults;
    data['total_pages'] = this.totalPages;

    return data;
  }
}
