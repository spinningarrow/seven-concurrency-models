let pkgs = import <nixpkgs> {};

in pkgs.stdenv.mkDerivation rec {
  name = "WordCount";

  buildInputs = with pkgs; [
    awscli
    maven
  ];
}
