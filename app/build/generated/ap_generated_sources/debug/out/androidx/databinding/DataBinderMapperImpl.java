package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.androidtutorialshub.project.DataBinderMapperImpl());
  }
}
