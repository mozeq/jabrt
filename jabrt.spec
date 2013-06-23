Name:           jabrt
Version:        1.0
Release:        1%{?dist}
Summary:		ABRT Java bindings

License:        Applications/System
URL:            https://github.com/mozeq/abrt-java-binding
Source0:        https://github.com/mozeq/abrt-java-binding/archive/%{commit}/%{name}-%{version}.tar.gz
BuildArch:      noarch

BuildRequires:  maven-local

%description
ABRT Java binging should provide a conve way to report problems

%package javadoc
Summary: API documentation for %{name}

%description javadoc
This package contains %{summary}.

%prep
%setup -q

%build
%mvn_build

%install
%mvn_install

%files -f .mfiles
%dir %{_javadir}/%{name}
%doc

%files javadoc -f .mfiles-javadoc
%doc

